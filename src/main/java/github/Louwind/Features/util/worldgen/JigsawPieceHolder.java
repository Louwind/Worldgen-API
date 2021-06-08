package github.Louwind.Features.util.worldgen;

import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.util.math.BlockBox;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JigsawPieceHolder implements StructurePiecesHolder {

    protected List<StructurePiece> pieces;

    @Override
    public void addPiece(StructurePiece piece) {
        this.pieces.add(piece);
    }

    @Nullable
    @Override
    public StructurePiece getIntersecting(BlockBox box) {
        return this.pieces.stream().filter(piece -> piece.getBoundingBox().intersects(box)).findFirst().orElse(null);
    }

    public List<StructurePiece> getPieces() {
        return this.pieces;
    }

}
